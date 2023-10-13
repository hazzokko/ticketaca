package com.ticketaca.sts.file;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



@Service
public class FileUploadService {

	/*파일이 업로드 될 경로(저장경로)*/
	private static String CURR_IMAGE_REPO_PATH = "C:\\ticket\\exh_image";
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/*@RequestMapping(value="/form") //최초 요청 URL: http://localhost:8090/sts/form
	public String form(){
		return "uploadForm";
	}//form() END
*/	
	
	//파일과 매개변수가 같이 저장되므로 Map을 사용
	@RequestMapping(value="/upload", method= RequestMethod.POST)
	public void upload(int exhNo, MultipartHttpServletRequest multipartRequest)
			throws Exception{ //multipartRequest 다중 요청?을 받기 떄문에?
		
		multipartRequest.setCharacterEncoding("UTF-8");
		
		// 파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map을 가져옴
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		
		// files.entrySet()의 요소를 읽어옴
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		
		MultipartFile mFile;
		
		// 파일이 업로드될 경로를 지정
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + exhNo + "\\";
		
		// 파일명이 중복되었을 경우, 사용할 스트링 객체
		String saveFileName = "", saveFilePath="";
				
		while(itr.hasNext()){
			
			Entry<String, MultipartFile> entry = itr.next();
			
			// entry에 값을 가져옴
			mFile = entry.getValue();
			
			// 파일명
			String fileName = mFile.getOriginalFilename();
			
			logger.info(fileName);
			
			// 확장자를 제외한 파일명
			String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
			
			// 확장자
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			// 저장될 경로와 파일명
			saveFilePath = filePath + File.separator + fileName;
			
			// filePath 에 해당되는 파일의 File 객체를 생성
			File fileFolder = new File(filePath);
			
			if(!fileFolder.exists()) {
				// 부모 폴더까지 포함하여 경로에 폴더를 만듦
				if(fileFolder.mkdirs()) {
					logger.info("[file.mkdirs] : Success");
				} else {
					logger.error("[file.mkdirs] : Fail");
				}
			}
			
			File saveFile = new File(saveFilePath);
			
			// saveFile이 File이면 true, 아니면 false
			// 파일명이 중복일 경우 파일명(1).확장자 같은 형태로 생성
			if(saveFile.isFile()) {
				boolean _exist = true;
				
				int index = 0;
				
				// 동일한 팡일명이 존재하지 않을 때까지 반복
				while(_exist) {
					index++;
					
					saveFileName = fileCutName + "(" + index + ")." + fileExt;
					String dictFile = filePath + File.separator + saveFileName;
					
					_exist = new File(dictFile).isFile();
					
					if(!_exist) {
						saveFilePath = dictFile;
					}
				}
				// 생성한 파일 객체를 업로드 처리하지 않으면 임시 파일에 저장된 파일이 자동 삭제되므로 transferTo(File f) 메서드를 이용해 업로드처리
				mFile.transferTo(new File(saveFilePath));
			} else {
				mFile.transferTo(saveFile);
			}
		}		
	}//upload() END
}//CLASS END

