package com.ticketaca.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ticketaca.sts.dao.ExhDAO;
import com.ticketaca.sts.vo.ExhVO;

@Service("exhService")
public class ExhServiceImpl implements ExhService {
	@Autowired
	private ExhDAO exhDAO;
	

	/* 전체 목록 출력 */
	public List<ExhVO> selectAllExhList() throws DataAccessException {
		List<ExhVO> exhsList = exhDAO.selectAllExhList();
		return exhsList;
	}
		
	/* 종료 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListClosing() throws DataAccessException {
		List<ExhVO> exhsList = exhDAO.selectExhListClosing();
		return exhsList;
	}

	/* 종료 예정 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListNearingDeadline() throws DataAccessException {
		List<ExhVO> exhsList = exhDAO.selectExhListNearingDeadline();
		return exhsList;
	}

	/* 진행 중 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListOngoing() throws DataAccessException {
		List<ExhVO> exhsList = exhDAO.selectExhListOngoing();
		return exhsList;
	}

	/* 예정 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListUpcoming() throws DataAccessException {
		List<ExhVO> exhsList = exhDAO.selectExhListUpcoming();
		return exhsList;
	}
	
	/* 전시 상세 정보 출력 */
	public ExhVO selectOneExh(int exhNo) throws DataAccessException {
		ExhVO exhVO = exhDAO.selectOneExh(exhNo);
		return exhVO;
	}

	/* 신규 전시 추가 */
	@Override
	public void insertExh(ExhVO exhVO) throws DataAccessException {
		exhDAO.insertExh(exhVO);
	}
	
	/* 전시 정보 수정 */
	@Override
	public void updateExh(ExhVO exhVO) throws DataAccessException {
		exhDAO.updateExh(exhVO);
		
	}
	
	/* 가장 큰 전시번호 찾기 */
	@Override
	public int findMaxExhNo() throws DataAccessException {
		return exhDAO.findMaxExhNo();
	}
	
	/* 전시 삭제 */
	@Override
	public int deleteExh(int exhNo) throws DataAccessException {
		return exhDAO.deleteExh(exhNo);
	}
}
