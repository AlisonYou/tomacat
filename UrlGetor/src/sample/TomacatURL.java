package sample;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TomacatURL {

    private String baseUrl,url,port,relativePath;

    public TomacatURL() {
        try {
            baseUrl = "http://"+ InetAddress.getLocalHost().getHostAddress()+":";
            url=baseUrl;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public TomacatURL(String baseUrl, String url, String port, String relativePath) {
        this();
        this.baseUrl = baseUrl;
        this.url = url;
        this.port = port;
        this.relativePath = relativePath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUrl() {
        return url+port+"/"+relativePath;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        if(port!=null){
            this.port = port;
        }

    }

    public String getRelativePath() {
        return relativePath;
    }
    public void setRelativePath(String relativePath){
        if(relativePath!=null){
            this.relativePath=relativePath;
        }

    }
}
