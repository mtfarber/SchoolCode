import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class TCPServer {
    public static void main(String argv[]) throws Exception {

        //Reads in the port and path of the files from the command line to initialize the server
        int port = Integer.parseInt(argv[0]);
        String rootPath = argv[1];

        ServerSocket welcomeSocket = new ServerSocket(port);

        //
        //Starts the connection to the server and parses the input stream from the connection after it is received.
        //Parses through the input stream to extract the command, path, and If-Modified-Since date from the HTTP Request.
        //After parsing, calls the response class to create the byte stream that will be output to the user.
        //
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            int errorCode = 0;
            String[] line = inFromClient.readLine().split(" ");
            String command = line[0];
            String path = line[1];

            String ifModifiedSince = null;
            while (inFromClient.ready()){
                String reader = inFromClient.readLine();
                if(reader.startsWith("If-Modified-Since:")){
                    ifModifiedSince = reader.substring(19);
                }
            }

            //Checks if given command is currently supperted by the server
            if (!command.equals("GET") && !command.equals("HEAD")) {
                errorCode = 501;
                System.out.println("501 error");
            }
            if (path.endsWith("/")) {
                path += "index.html";
            }
            path = rootPath + path;

            System.out.println("Command: " + command);
            System.out.println("Path: " + path);
            System.out.println("IfModifiedSince: " + ifModifiedSince);

            //Checks to see if the date in the HTTP request is in the appropriate format otherwise a 400
            //error code is set
            Date date = new Date(0);
            if (ifModifiedSince != null) {
                try{
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
                    date = format.parse(ifModifiedSince);

                } catch (ParseException e){
                    errorCode = 400;
                    System.out.println("Date Parse Error");
                }
            }

            //Response object is created and the data is send out to the user
            HTTPResponse response = new HTTPResponse(command, path, date, errorCode);
            DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());
            response.sendResponse(out);
            out.close();
            connectionSocket.close();
        }
    }
}


class HTTPResponse {
    private String statusLine;
    private String date;
    private String server;
    private String lastModified;
    private String contentLength;
    private byte[] fileContent;

    public HTTPResponse(String command, String path, Date ifModifiedSince, int errorCode) {
        try {
            File file = new File(path);

            //Ensures that the file exists and can be returned to the browser for viewing, otherwise an error code is set
            if (!file.exists() || file.isDirectory()){
                System.out.println("404 error");
                errorCode = 404;
            }

            //Checks the modified date and determines whether or not a file needs to be returned, since it is out of date
            System.out.println("Error code before 304 check: " + errorCode);
            System.out.println("Date: " + ifModifiedSince);
            System.out.println("Last Modified: " + new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy").format(file.lastModified()));
            System.out.println(ifModifiedSince);
            System.out.println("Last modified before ifModifiedSince: " + new Date(file.lastModified()).before(ifModifiedSince));
            if(ifModifiedSince != new Date(0) && new Date(file.lastModified()).before(ifModifiedSince)){
                errorCode = 304;
            }
            System.out.println("Error code after 304 check: " + errorCode);


            System.out.println("File exists?: " + file.exists());
            System.out.println("Is a directory?: " + file.isDirectory());
            System.out.println("Error code before response: " + errorCode);

            //Uses current error code status to determine what to write to the output stream for the user to view
            //An error code of 0 means everything is OK and is the same as status 200
            if (errorCode == 0) {
                System.out.println("200 OK Response");
                statusLine = "HTTP/1.1 200 OK";
            } else if (errorCode == 501) {
                System.out.println("501 Error Response");
                statusLine = "HTTP/1.1 501 NOT IMPLEMENTED";
                fileContent = "Error 501: Not Implemented".getBytes();
                contentLength = "Content-Length: " + fileContent.length;
            } else if (errorCode == 400){
                System.out.println("400 Error Response");
                statusLine = "HTTP/1.1 400 BAD REQUEST";
                fileContent = "Error 400: Bad Request".getBytes();
                contentLength = "Content-Length: " + fileContent.length;
            } else if (errorCode == 304){
                System.out.println("304 Error Response");
                statusLine = "HTTP/1.1 304 NOT MODIFIED";
            } else if (errorCode == 404){
                System.out.println("404 Error Response");
                statusLine = "HTTP/1.1 404 NOT FOUND";
                fileContent = "Error 404: NOT FOUND".getBytes();
                contentLength = "Content-Length: " + fileContent.length;
            }

            //Adds the date and server header fields to every response
            date = "Date: " + new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy").format(new Date());
            server = "Server: Matts Server";

            //If error code is still 0, write the last modified and content length fields to the output stream
            //After, the files content is also written to the output stream if it is needed by the HTTP request
            if (errorCode == 0) {
                lastModified = "Last-Modified: " + new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy").format(file.lastModified());
                contentLength = "Content-Length: " + file.length();

                if (command.equals("GET")){
                    System.out.println("Running inside the GET block");
                    System.out.println("GET command Response");
                    fileContent = new byte[(int) file.length()];
                    FileInputStream fis = new FileInputStream(file);
                    fis.read(fileContent);
                    fis.close();
                }
            }
        } catch (IOException e) {
            System.out.println("500 Error Response");
            statusLine = "HTTP/1.1 500 Internal Server Error";
            date = "Date: " + new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy").format(new Date());
            server = "Server: SimpleJavaHTTPServer";
        }
    }

    public void sendResponse(DataOutputStream out) throws IOException {
        out.writeBytes(statusLine + "\r\n");
        out.writeBytes(date + "\r\n");
        out.writeBytes(server + "\r\n");

        if (lastModified != null) {
            out.writeBytes(lastModified + "\r\n");
        }

        if (contentLength != null) {
            out.writeBytes(contentLength + "\r\n");
        }

        out.writeBytes("\r\n");

        if (fileContent != null) {
            out.write(fileContent);
        }

        out.flush();
    }
}

