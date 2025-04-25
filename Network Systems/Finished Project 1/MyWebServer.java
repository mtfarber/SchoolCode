
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        int port = Integer.parseInt(argv[0]);
        String rootPath = argv[1];

        ServerSocket welcomeSocket = new ServerSocket(port);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String requestString = inFromClient.readLine();
            HTTPRequest request = new HTTPRequest(requestString, rootPath);
            int errorCode = request.getErrorCode();
            connectionSocket.close();
        }
    }
}

class HTTPRequest {
    private String command;
    private String path;
    private String ifModifiedSince;
    private int errorCode;

    public HTTPRequest(String requestString, String rootPath) {
        StringTokenizer tokenizer = new StringTokenizer(requestString);
        if (tokenizer.countTokens() < 2) {
            errorCode = 400;
            return;
        }

        command = tokenizer.nextToken().toUpperCase();
        path = tokenizer.nextToken();

        System.out.println("Command: " + command);
        System.out.println("Original Path: " + path);


        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equalsIgnoreCase("If-Modified-Since:")) {
                ifModifiedSince = tokenizer.nextToken("\r\n");
                try {
                    new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").parse(ifModifiedSince);
                } catch (ParseException e) {
                    errorCode = 400;
                    return;
                }
                break;
            }
        }

        System.out.println("If modified since date: " + ifModifiedSince);

        if (!command.equals("GET") && !command.equals("HEAD")) {
            errorCode = 501;
            return;
        }

        if (path.endsWith("/")) {
            path += "index.html";
        }
        System.out.println("Path after adding index: " + path);

        path = rootPath + path;

        System.out.println("Root path plus path: " + path);
        System.out.println(errorCode);
    }

    public String getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }

    public String getIfModifiedSince() {
        return ifModifiedSince;
    }

    public int getErrorCode() {
        return errorCode;
    }
}


