package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MessageConverter {

	public static void resolve(Object data, HttpServletResponse response) {

		try {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter(); // 요청한 클라이언트에 버퍼를 달았다. (데이터전송해주려고)
			// out.println(memberEntity); // json이 아니라 이해 못함
			// ViewResolver말고 MessageConverter를 사용해보자. (gson으로 파싱)
			Gson gson = new Gson();
			String responseBody = gson.toJson(data);
			out.println(responseBody);
			out.flush();
		} catch (Exception e) {
			writeError(response);
		}
	}

	private static void writeError(HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>JSON 변환에 실패하였습니다.</h1>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
