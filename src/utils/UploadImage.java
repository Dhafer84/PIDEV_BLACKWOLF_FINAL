package utils;



import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadImage {

    public static String Uplaod(File fileToUpload, String id) throws IOException {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    			  .build();
    	String url = "http://localhost:3030/api/v1/users/upload";
    			RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
    			  .addFormDataPart("user_id",id)
    			  .addFormDataPart("photo",fileToUpload.getName(),
    			    RequestBody.create(MediaType.parse("image/jpeg"),
    			    		fileToUpload))
    			  .build();
    			Request request = new Request.Builder()
    			  .url(url)
    			  .method("POST", body)
    			  .build();
    			Response response = client.newCall(request).execute();
    			
    			String jsonData = response.body().string();
    		    JSONObject Jobject = null;
    		    String responseStatus = null;
    		    String imageName = null;
				try {
					Jobject = new JSONObject(jsonData);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		    try {
					responseStatus = Jobject.getString("status");
					imageName = Jobject.getString("image_name");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		    
    		    System.out.println(responseStatus);
    		    System.out.println(imageName);
    		    if(responseStatus.equals("success")) {
    		    	return imageName;
    		    }
    		    return "";
    }
}