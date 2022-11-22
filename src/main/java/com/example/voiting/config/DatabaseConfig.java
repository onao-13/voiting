package com.example.voiting.config;

import com.example.voiting.system.Database;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
public class DatabaseConfig {

    @PostConstruct
    private void postConstruct() throws IOException {
        FirebaseApp.initializeApp(getFirebaseOptions());
    }

    @Bean
    FileInputStream getAccountService() throws FileNotFoundException {
        File file = new File("accountService.json");
        try(FileWriter fileWriter = new FileWriter(file)) {
            String data = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"voiting-db\",\n" +
                    "  \"private_key_id\": \"0453d737bb635f114d7e61836d5441717a7248d6\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCc/fT90ZIUAMLB\\n4f4BnxR+ttc0sCCkfDflDdwltyYX0z8hf0VJsNguiHn888FvPRLnOZd1Vh2JNcL+\\nsXRIRgakENQpdun+iGc+BfSjquFNEs+QigTfWLmar1ozIhO+55772orvK1QNUdQD\\nkXXaHySrugBc6fEvd4pRt4xflUKorL1qjRRAtP0riFU/b5zr4xHvr5lXjhYnTEI1\\nBTJ7u9rcgoFFqua61IpP8R/TuiMHQXgT1qo4p++7BypqBo19wrGcTyRorDnvba2o\\n1BuTLVAJqfLcIB65H1KzsQLFj8rM+xJ/rfiegzNOuIQ95q6UGe7f8UXiwgSEIsrM\\nsJzywrvzAgMBAAECggEAFRzMDTRZVkpR5rwLKlGn8nCJWYMUKLqx06J9CC9stoiI\\ntWsFuObI890TtrJbQafvelwS48s8dp7dxd28exm7u+WtepQM3NegNsWbREVvPiu4\\n8UG2bQjkEQri8Px0tlg0177or0he6dmmzwIJXNeSWWvOr7AgaNdFKT8raBFNIRzA\\nAVcxlzPyYmu6dSTzcJ4Lw+Oype7jkY2W+inFMpOuo0gtbxblDES3cY4I8iryfrcJ\\n1FEQADY/GRiqAPoDH73muQXuH9jNRBvDV2HlSTbfjGoD2QppvJPHITyBRFei7j3/\\nmFGyDU+sNIE3sw1evegyK6KullAjZApf5975xnXCFQKBgQDLReHzCPDehvGmFtX9\\nleszumDC3x2MHd1aF1DDiCcl4lLqrb+3t2LAbj/R3mIaOfw8EH4E//XXl988992c\\niGrcd8d4M0+RnKsn04wraB0PUT9eC6uJhjJfU2dh8q7SxiBqtfum+MS2w4RMRiec\\nNW3ZAgxZc5PRtq2apr4hQberZwKBgQDFttakp9No+fPameItgE2/N7hKGaVQ/dMZ\\n6J2Mhm2ebIDPYsTS7om3dVX336Y8qM1B11CtbwFS36UG+T1QeEw+fvnSD/PPui20\\njMn5SRt4uliYENusvcRHn+jjpjkLQkyufQUxIT4dnXnz9mERuQXOrddhql7Q7PrY\\nrrra7AGflQKBgBYkh6d0yuBCrOIdzLOx+AwLemzf5xUTT8B3l7mlBDaxP70GrkKx\\n/FTf7Z3L7r3SoTJdzYx34WzZw1yd14sspQ9dEOJ8HGfNPCu7dKJp1fiMLi3tcYMq\\nliQiLrnRuM+G+5MEPeSDZtJN3AR/A+OVQmEIQxYfa/PFRYX6Me3nfb1zAoGBAMKY\\nRUREJfDBaoAySwmLqXnVpp3QBgTj/f0XFNG1kXiGxHTZem089/DIHLfduOklHvh5\\nql84zS27DtsBPpbQAALWyJ02tDo0vT1iAHxJty8pAX+RwBSRpfj066v781MgiwaF\\n6DFFzbQqRViXm0xtNckJEt5+Z9nsFKxltWU5OhPNAoGAFa4KbgLSYFkoSsJ2+XzN\\nCTJ5zUbSeLjYxMgf6OdLKKv0TUIA2ttB6wBONd4q9H7dmFI9dcXJWOGTfBhNEHUL\\np+UZJyUvIwOn/LrYBS9C/+R86hqEsdh6YEXLAAb+xS+VL/f49X+Bmw7yARZ7GknK\\nREkBV4bFv7Eeoj0yfV8fTik=\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-h7nth@voiting-db.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"113616824289691376500\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-h7nth%40voiting-db.iam.gserviceaccount.com\"\n" +
                    "}\n";
            fileWriter.write(data);
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
        return new FileInputStream(file);
    }

    @Bean
    FirebaseOptions getFirebaseOptions() throws IOException {
        return FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(getAccountService()))
            .build();
    }

    @Bean
    Database createDb() {
        return new Database();
    }
}
