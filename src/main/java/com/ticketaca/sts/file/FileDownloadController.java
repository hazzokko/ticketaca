package com.ticketaca.sts.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컴포넌트 스캔이 스캔 가능
public class FileDownloadController {
	
	/*이미지 불러올 경로*/ //업로드가 끝난 후에, 파일이 저장되는 위치 -> 즉 가져올(다운로드할) 경로
	private static String CURR_IMAGE_REPO_PATH = "C:\\ticket\\exh_image";
	

	//[테스트용] result.jsp(결과화면)에서 업로드된 파일을 다시 다운로드하여 사용자에게 보여줄 때 호출.
		@RequestMapping(value = "/download")
		public void download(@RequestParam int exhNo, @RequestParam String exhThumbImg, HttpServletResponse response) 
				throws Exception{
		/*@RequestMapping(value = {"/download/{exhNo}/{exhThumbImg}"})
		public void download(@PathVariable int exhNo, @PathVariable String exhThumbImg, HttpServletResponse response) 
				throws Exception{*/
//			response.setContentType("image/png");
		
			System.out.println("download");
			// 인터넷 상에서 파일을 다룰 때(보내고 받을때) stream 형식
			OutputStream out = response.getOutputStream(); //서버에서 응답이므로 response이고, getOutputSream으로 OutputStream 객체를 얻음.
//			String filePath = CURR_IMAGE_REPO_PATH + "\\" + exhNo + "\\" + exhThumbImg; // 파일 주소 및 파일명을 조합
			String filePath = CURR_IMAGE_REPO_PATH + "\\" + exhNo + "\\" + exhThumbImg; // 파일 주소 및 파일명을 조합
//			String filePath = CURR_IMAGE_REPO_PATH + "\\" + exhNo + "\\" + exhThumbImg + ".png"; // 파일 주소 및 파일명을 조합
			File image = new File(filePath);		//원본 파일 객체
			System.out.println("filePath ==> " + filePath);
			System.out.println("image ==> " + image);
			
			/*int lastIndex = exhThumbImg.lastIndexOf(".");
			String fileName = exhThumbImg.substring(0,lastIndex);
			
			File thumbnail = new File(CURR_IMAGE_REPO_PATH + "\\" + exhNo + "\\" + fileName +".png");*/
			//썸네일 라이브러리가 가진 메소드는 파일 객체를 받게 되어 있어서 2개 파일 객체가 필요하다?
			//이름 뒤에 png를 붙이는 것으로는 실제 파일 내부가 png 형식으로 바뀌지 않음. 
			//썸네일 라이브러리 코드에서 OutputFormat을 png라고 명시해주어야 함. -> 실제로 내부 파일의 형태를 png로 바꿔줌
			
			/*if(image.exists()){
				thumbnail.getParentFile().mkdirs();
				Thumbnails.of(image).size(100, 100).outputFormat("png").toFile(thumbnail);
			}*/

			
//			FileInputStream in = new FileInputStream(thumbnail); //유저가 읽어오는 것
			FileInputStream in = new FileInputStream(image); //유저가 읽어오는 것
			System.out.println("image ==> " + image);
			byte[] buffer = new byte[1024 * 8]; //기본 8kb -> 바이트 배열을 만듬
			
			while(true){
				int count = in.read(buffer);  	//버퍼에 읽어들인 문자 갯수
//				System.out.println("버퍼확인용 count : " + count);
				if(count == -1) 				//버퍼의 마지막에 도달했는지 체크, 인덱스번호가 -1이면 끝을 의미 -> 종료
					break;
				out.write(buffer, 0, count);	//끝나지 않았다면 계속하여 쓴다.
					//    buffer배열의  0번인덱스부터 ~ count 인덱스까지
	 		}
			
			in.close();
			out.close();
		}//download() END
}//CLASS END
