/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.*;

/**
 *
 * @author Yassin
 */
public class CertificadoTitularidad {
    
    public static void generarCertTitular(String ruta, Cliente cliente) throws IOException{
        BufferedWriter bf = new BufferedWriter(new FileWriter(ruta));
        String certificado = 
            "<!DOCTYPE html>\n" +
            "<html lang=\"es\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Certificado de Titularidad de Cuenta</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            background-color: #f4f4f4;\n" +
            "        }\n" +
            "        .container {\n" +
            "            width: 80%;\n" +
            "            margin: 50px auto;\n" +
            "            padding: 20px;\n" +
            "            background-color: white;\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
            "        }\n" +
            "        .header {\n" +
            "            text-align: center;\n" +
            "            margin-bottom: 40px;\n" +
            "        }\n" +
            "        .header img {\n" +
            "            max-width: 150px;\n" +
            "        }\n" +
            "        .header h1 {\n" +
            "            margin: 20px 0 10px;\n" +
            "        }\n" +
            "        .content {\n" +
            "            line-height: 1.6;\n" +
            "        }\n" +
            "        .content p {\n" +
            "            margin: 10px 0;\n" +
            "        }\n" +
            "        .footer {\n" +
            "            margin-top: 40px;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "        .signature {\n" +
            "            margin-top: 50px;\n" +
            "            text-align: right;\n" +
            "        }\n" +
            "        .signature img {\n" +
            "            max-width: 200px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"container\">\n" +
            "    <div class=\"header\">\n" +
            "        <h1>Proyecto Final.</h1>\n" +
            "        <h2>Certificado de Titularidad de Cuenta</h2>\n" +
            "    </div>\n" +
            "    <div class=\"content\">\n" +
            "        <p>Por la presente, se certifica que:</p>\n" +
            "        <p><strong>Nombre del Titular:</strong> "+cliente.getNombre()+" "+cliente.getApellido()+" </p>\n" +
            "        <p><strong>Número de Cuenta:</strong> "+cliente.getCuenta().getNumCuenta()+ " </p>\n" +
            "        <p><strong>DNI del Titular:</strong> "+cliente.getDNI()+" </p>\n" +
            "        <p><strong>Banco Emisor:</strong> Proyecto Final</p>\n" +
            "        <p>Es titular de la cuenta bancaria indicada anteriormente, la cual se encuentra activa y en plena disposición del titular.</p>\n" +
            "        <p>Este certificado se expide a solicitud del interesado, a los efectos que estime oportunos.</p>\n" +
            "    </div>\n" +
            "    <div class=\"signature\">\n" +
            "        <p>Atentamente,</p>\n" +
            "        <p><strong>Yassin charrouf errynda</strong></p>\n" +
            "        <p>Gerente de Sucursal</p>\n" +
            "    </div>\n" +
            "    <div class=\"footer\">\n" +
            "        <p>Proyecto Final| Dirección del Banco | Teléfono: 123-456-789 | Email: info@bancoejemplo.com</p>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

            bf.write(certificado);
            bf.close();
    }
}
