//import java.net.*;
//import java.io.*;
//import java.util.Timer;
//
//class StudentSocketImpl extends BaseSocketImpl {
//
//    // SocketImpl data members:
//    //   protected InetAddress address;
//    //   protected int port;
//    //   protected int localport;
//
//    private Demultiplexer D;
//    private Timer tcpTimer;
//
//    private boolean isClient;
//
//    public enum CurrentState {
//        CLOSED,
//        LISTEN,
//        SYN_RCVD,
//        FIN_WAIT_1,
//        FIN_WAIT_2,
//        ESTABLISHED,
//        SYN_SENT,
//        CLOSE_WAIT,
//        LAST_ACK,
//        CLOSING,
//        TIME_WAIT;
//
//        public CurrentState changeToState(CurrentState newState){
//            System.out.println("!!! " + this + "->" + newState);
//            return newState;
//        }
//    }
//
//
//
//    StudentSocketImpl(Demultiplexer D) {  // default constructor
//        this.D = D;
//    }
//
//    /**
//     * Connects this socket to the specified port number on the specified host.
//     *
//     * @param      address   the IP address of the remote host.
//     * @param      port      the port number.
//     * @exception  IOException  if an I/O error occurs when attempting a
//     *               connection.
//     */
//
//    CurrentState currentState = CurrentState.CLOSED;
//
//    public synchronized void connect(InetAddress address, int port) throws IOException{
//        localport = D.getNextAvailablePort();
//        this.address = address;
//        this.port = port;
//        isClient = true;
//
//        //initialize the state
//        currentState = currentState.changeToState(CurrentState.SYN_SENT);
//        //register the connection
//        D.registerConnection(address, localport, port, this);
//
//        //create the syn packet and send it
//        TCPPacket packet = new TCPPacket(localport, port, 100, -1, false, true, false, 1, new byte[0]);
//        TCPWrapper.send(packet, address);
//
//        while(currentState != CurrentState.ESTABLISHED){
//            try{
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Called by Demultiplexer when a packet comes in for this connection
//     * @param p The packet that arrived
//     */
//    public synchronized void receivePacket(TCPPacket p){
//        System.out.println("Packet received from address " + p.sourceAddr + " with seqNum " + p.seqNum + " is being processed.");
//        if(p.ackFlag && !(p.synFlag)){System.out.println("The packet is an ack.");}
//        else if (p.synFlag){System.out.println("The packet is a syn.");}
//        else if (p.finFlag){System.out.println("The packet is a fin.");}
//        else if (p.synFlag && p.ackFlag){System.out.println("The packet is a syn-ack.");}
//
//        //Keeping track of the state of the connection
//        switch(currentState) {
//            case LISTEN:
//                if (p.synFlag) {
//                    //change state to SYN_RCVD
//                    currentState = currentState.changeToState(CurrentState.SYN_RCVD);
//
//                    //update address and port on the server side
//                    this.address = p.sourceAddr;
//                    this.port = p.sourcePort;
//
//                    //send SYN + ACK packet
//                    TCPPacket packet = new TCPPacket(p.destPort, p.sourcePort, p.ackNum, p.seqNum+1, true, true, false, 1, new byte[0]);
//                    TCPWrapper.send(packet, p.sourceAddr);
//
//                    //unregister as a listening connection
//                    try {
//                        D.unregisterListeningSocket(p.destPort, this);
//                    } catch (IOException e) {
//                        System.err.println("An IOException occurred: " + e.getMessage());
//                    }
//
//                    //register as a regular connection
//                    try {
//                        D.registerConnection(p.sourceAddr, p.destPort, p.sourcePort, this);
//                    } catch (IOException e) {
//                        System.err.println("An IOException occurred: " + e.getMessage());
//                    }
//                }
//                break;
//
//            case SYN_SENT:
//                if(p.synFlag  && p.ackFlag){
//                    currentState = currentState.changeToState(CurrentState.ESTABLISHED);
//                    //send ACK
//                    TCPPacket packet = new TCPPacket(p.destPort, p.sourcePort, p.ackNum, p.seqNum+1, true, false, false, 1, new byte[0]);
//                    TCPWrapper.send(packet, p.sourceAddr);
//                }
//                break;
//
//            case SYN_RCVD:
//                if(p.ackFlag){
//                    currentState = currentState.changeToState(CurrentState.ESTABLISHED);
//                }
//                break;
//
//            case FIN_WAIT_1:
//                if (p.finFlag){
//                    //change to closing state
//                    currentState = currentState.changeToState(CurrentState.CLOSING);
//
//                    //send ack packet
//                    TCPPacket packet = new TCPPacket(p.destPort, p.sourcePort, p.ackNum, p.seqNum+1, true, false, false, 1, new byte[0]);
//                    TCPWrapper.send(packet, p.sourceAddr);
//
//                }
//                if (p.ackFlag){
//                    //change to FIN_WAIT_2 state
//                    currentState = currentState.changeToState(CurrentState.FIN_WAIT_2);
//                }
//                break;
//
//            case ESTABLISHED:
//                if (p.finFlag && !isClient){
//                    //change to CLOSE_WAIT state
//                    currentState = currentState.changeToState(CurrentState.CLOSE_WAIT);
//
//                    //send ack packet
//                    TCPPacket packet = new TCPPacket(p.destPort, p.sourcePort, p.ackNum, p.seqNum+1, true, false, false, 1, new byte[0]);
//                    TCPWrapper.send(packet, p.sourceAddr);
//                }
//                break;
//
//            case FIN_WAIT_2:
//                if(p.finFlag){
//                    //change to TIME_Wait state
//                    currentState = currentState.changeToState(CurrentState.TIME_WAIT);
//
//                    //send ack
//                    TCPPacket packet = new TCPPacket(p.destPort, p.sourcePort, p.ackNum, p.seqNum+1, true, false, false, 1, new byte[0]);
//                    TCPWrapper.send(packet, p.sourceAddr);
//
//                    //maybe start a 30 second timer
//                    //maybe do something with the timer
//                }
//                break;
//
//            case LAST_ACK:
//                if(p.ackFlag){
//                    //change to TIME_WAIT state
//                    currentState = currentState.changeToState(CurrentState.TIME_WAIT);
//                }
//                break;
//
//            case CLOSING:
//                if(p.ackFlag){
//                    //change to TIME_WAIT state
//                    currentState = currentState.changeToState(CurrentState.TIME_WAIT);
//                }
//                break;
//
//        }
//        //Notify all threads of new state
//        this.notifyAll();
//    }
//
//    /**
//     * Waits for an incoming connection to arrive to connect this socket to
//     * Ultimately this is called by the application calling
//     * ServerSocket.accept(), but this method belongs to the Socket object
//     * that will be returned, not the listening ServerSocket.
//     * Note that localport is already set prior to this being called.
//     */
//    public synchronized void acceptConnection() throws IOException {
//        //initialize state
//        currentState = currentState.changeToState(CurrentState.LISTEN);
//        D.registerListeningSocket(localport, this);
//        isClient = false;
//
//        while(currentState != CurrentState.ESTABLISHED && currentState != CurrentState.SYN_RCVD){
//            try{
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    /**
//     * Returns an input stream for this socket.  Note that this method cannot
//     * create a NEW InputStream, but must return a reference to an
//     * existing InputStream (that you create elsewhere) because it may be
//     * called more than once.
//     *
//     * @return     a stream for reading from this socket.
//     * @exception  IOException  if an I/O error occurs when creating the
//     *               input stream.
//     */
//    public InputStream getInputStream() throws IOException {
//        // project 4 return appIS;
//        return null;
//
//    }
//
//    /**
//     * Returns an output stream for this socket.  Note that this method cannot
//     * create a NEW InputStream, but must return a reference to an
//     * existing InputStream (that you create elsewhere) because it may be
//     * called more than once.
//     *
//     * @return     an output stream for writing to this socket.
//     * @exception  IOException  if an I/O error occurs when creating the
//     *               output stream.
//     */
//    public OutputStream getOutputStream() throws IOException {
//        // project 4 return appOS;
//        return null;
//    }
//
//
//    /**
//     * Closes this socket.
//     *
//     * @exception  IOException  if an I/O error occurs when closing this socket.
//     */
//    public synchronized void close() throws IOException {
//        System.out.println("*** close() was called by the application");
//        //state change
//        if (currentState==CurrentState.ESTABLISHED && isClient){
//            //update state
//            currentState = currentState.changeToState(CurrentState.FIN_WAIT_1);
//
//            //send fin packet
//            TCPPacket finPacket = new TCPPacket(localport, port, 100, -2, false, false, true, 1, new byte[0]);
//            TCPWrapper.send(finPacket, address);
//
//            //create timer
//            createTimerTask(30000, null);
//        }
//        else if (!isClient && currentState==CurrentState.ESTABLISHED){
//            while (currentState!=CurrentState.CLOSE_WAIT){
//                try{
//                    wait();
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//            //update state
//            currentState = currentState.changeToState(CurrentState.LAST_ACK);
//            //send fin packet
//            TCPPacket finPacket = new TCPPacket(localport, port, 100, -2, false, false, true, 1, new byte[0]);
//            TCPWrapper.send(finPacket, address);
//
//            //create timer
//            createTimerTask(30000, null);
//        }
//        else if (!isClient && currentState==CurrentState.CLOSE_WAIT){
//            //update state
//            currentState = currentState.changeToState(CurrentState.LAST_ACK);
//            //send fin packet
//            TCPPacket finPacket = new TCPPacket(localport, port, 100, -2, false, false, true, 1, new byte[0]);
//            TCPWrapper.send(finPacket, address);
//
//            //create timer
//            createTimerTask(30000, null);
//        }
//        else{
//            System.out.println("Attempted to close while not established (ESTABLISHED) or waiting to close (CLOSE_WAIT)");
//        }
//        this.notifyAll();
//
//    }
//
//    /**
//     * create TCPTimerTask instance, handling tcpTimer creation
//     * @param delay time in milliseconds before call
//     * @param ref generic reference to be returned to handleTimer
//     */
//    private TCPTimerTask createTimerTask(long delay, Object ref){
//        if(tcpTimer == null)
//            tcpTimer = new Timer(false);
//        return new TCPTimerTask(tcpTimer, delay, this, ref);
//    }
//
//
//    /**
//     * handle timer expiration (called by TCPTimerTask)
//     * @param ref Generic reference that can be used by the timer to return
//     * information.
//     */
//    public synchronized void handleTimer(Object ref){
//
//        // this must run only once the last timer (30 second timer) has expired
//        tcpTimer.cancel();
//        tcpTimer = null;
//    }
//}
