import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class TCPServer {
    public static void main(String argv[]) throws Exception {
        int port = Integer.parseInt(argv[0]);
        String rootPath = argv[1];

        ServerSocket welcomeSocket = new ServerSocket(port);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //String requestString = inFromClient.readLine();
            while (inFromClient.ready()){
                System.out.println(inFromClient.readLine());
            }

            String requestString = builder.toString();
            //System.out.println(requestString);


            HTTPRequest request = new HTTPRequest(requestString, rootPath);
            int errorCode = request.getErrorCode();
            String filePath = request.getPath();
            HTTPResponse response = new HTTPResponse(request, filePath, errorCode);
            DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());
            response.sendResponse(out);
            out.close();
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
        System.out.println("Request: " + requestString);
        StringTokenizer tokenizer = new StringTokenizer(requestString);
        System.out.println("Token count: " + tokenizer.countTokens());
        if (tokenizer.countTokens() < 2) {
            errorCode = 400;
            return;
        }

        command = tokenizer.nextToken().toUpperCase();
        System.out.println("Command: " + command);
        path = tokenizer.nextToken();
        System.out.println("Path: " + path);

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
                System.out.println("If modified since: " + ifModifiedSince);
                break;
            }
            //System.out.println("Token: " + token);
        }



        if (!command.equals("GET") && !command.equals("HEAD")) {
            errorCode = 501;
            return;
        }

        if (path.endsWith("/")) {
            path += "index.html";
        }

        path = rootPath + path;
        System.out.println(ifModifiedSince);
        if (ifModifiedSince != null) {
            try {
                Date ifModifiedSinceDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").parse(ifModifiedSince);
                Date currentDate = new Date();
                if (ifModifiedSinceDate.after(currentDate)) {
                    errorCode = 304;
                    return;
                }
            } catch (ParseException e) {
                errorCode = 400;
                return;
            }
        }
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



class HTTPResponse {
    private String statusLine;
    private String date;
    private String server;
    private String lastModified;
    private String contentLength;
    private byte[] fileContent;

    public HTTPResponse(HTTPRequest request, String filePath, int errorCode) {
        try {
            File file = new File(filePath);
            System.out.println("Matching: " + errorCode);

            if (file.exists() && file.isDirectory() && errorCode == 0) {
                statusLine = "HTTP/1.1 200 OK";
            } else if (errorCode == 501) {
                statusLine = "HTTP/1.1 501 NOT IMPLEMENTED";
                fileContent = "Error 501: Not Implemented".getBytes();
                contentLength = "Content-Length: " + fileContent.length;
            } else if (errorCode == 400){
                statusLine = "HTTP/1.1 400 BAD REQUEST";
                fileContent = "Error 400: Bad Request".getBytes();
                contentLength = "Content-Length: " + fileContent.length;
            } else if (errorCode == 304){
                statusLine = "HTTP/1.1 304 NOT MODIFIED";
            } else {
                statusLine = "HTTP/1.1 404 NOT FOUND";
                fileContent = "Error 404: NOT FOUND".getBytes();
                contentLength = "Content-Length: " + fileContent.length;
            }

            date = "Date: " + new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date());
            server = "Server: SimpleJavaHTTPServer";

            if (errorCode == 0) {
                lastModified = "Last-Modified: " + new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(file.lastModified());
                contentLength = "Content-Length: " + file.length();

                if (request.getCommand().equals("GET")) {
                    fileContent = new byte[(int) file.length()];
                    FileInputStream fis = new FileInputStream(file);
                    fis.read(fileContent);
                    fis.close();
                }
            }
        } catch (IOException e) {
            statusLine = "HTTP/1.1 500 Internal Server Error";
            date = "Date: " + new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date());
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