package edu.eci.arep.app;

import java.util.List;

import static spark.Spark.*;

public class Spark {

    private static ConjeturaCollatz conjeturaCollatz = ConjeturaCollatz.getInstance();

    public static void main(String... args){
        port(getPort());
        get("/", (req,res) -> index());
        get("collatz", (req,res) -> {
            String value = req.queryParams("value");
            res.type("application/json");
            List<Integer> secuence = conjeturaCollatz.enterNumber(value);
            StringBuilder result = new StringBuilder();
            for(Integer integer: secuence){
                result.append(integer.toString());
                result.append("->");
            }
            return "{ \"operation\": \"collatzsequence\", \"input\": \""+ value +"\", \"output\": \""+ result +"\"}";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String index(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Parcial</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Collatz</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"13\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/collatz?value=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "    </body>\n" +
                "</html>";
    }
}
