package com.ticketaca.sts.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ticketaca.sts.vo.ExhVO;

@Repository("exhDAO")
public class ExhDAOImpl implements ExhDAO {
	@Autowired
	private SqlSession sqlSession;
	
	/* 전체 전시 목록 출력 */
	@Override
	public List<ExhVO> selectAllExhList() throws DataAccessException {
		List<ExhVO> exhsList = sqlSession.selectList("mapper.ticketaca.selectAllExhList");
		return exhsList;
	}
	
	/* 종료 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListClosing() throws DataAccessException {
		List<ExhVO> exhsList = sqlSession.selectList("mapper.ticketaca.selectExhListClosing");
		return exhsList;
	}
	
	/* 종료 예정 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListNearingDeadline() throws DataAccessException {
		List<ExhVO> exhsList = sqlSession.selectList("mapper.ticketaca.selectExhListNearingDeadline");
		return exhsList;
	}
	
	/* 진행 중 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListOngoing() throws DataAccessException {
		List<ExhVO> exhsList = sqlSession.selectList("mapper.ticketaca.selectExhListOngoing");
		return exhsList;
	}
	
	/* 예정 전시 목록 출력 */
	@Override
	public List<ExhVO> selectExhListUpcoming() throws DataAccessException {
		List<ExhVO> exhsList = sqlSession.selectList("mapper.ticketaca.selectExhListUpcoming");
		return exhsList;
	}
		
	/* 전시 상세 정보 출력 */
	@Override
	public ExhVO selectOneExh(int exhNo) throws DataAccessException {
		ExhVO exhVO = sqlSession.selectOne("mapper.ticketaca.selectOneExh", exhNo);
		return exhVO;
	}
	
	/* 가장 큰 전시번호 찾기 */
	@Override
	public int findMaxExhNo() throws DataAccessException {
		return sqlSession.selectOne("mapper.ticketaca.findMaxExhNo");
	}

	/* 신규 전시 추가 */
	@Override
	public void insertExh(ExhVO exhVO) throws DataAccessException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DAO");
		sqlSession.insert("mapper.ticketaca.insertExh", exhVO);
		
	}
	
	/* 전시 정보 수정 */
	@Override
	public void updateExh(ExhVO exhVO) throws DataAccessException {
		sqlSession.update("mapper.ticketaca.updateExh", exhVO);		
	}
	
	/* 전시 삭제 */
	@Override
	public int deleteExh(int exhNo) throws DataAccessException {
		int result = sqlSession.delete("mapper.ticketaca.deleteExh", exhNo);
		return result;
	}	
}
