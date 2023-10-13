package com.ticketaca.sts.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ticketaca.sts.vo.ZzimVO;


@Repository("zzimDAO")
public class ZzimDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	/** jcm / zzim : 찜 목록 조회 **/
	public List<ZzimVO> zzimList(String userId) throws DataAccessException {
		List<ZzimVO> zzimList = sqlSession.selectList("mapper.ticketaca.zzimList", userId);
		return zzimList;
	}//zzimList() END
	
	/** jcm / zzim : [REST] 찜 삭제 **/
	public boolean zzimRemove(int zzNo) throws DataAccessException {
		int result = sqlSession.delete("mapper.ticketaca.zzimRemove", zzNo);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}//zzimRemove() END
}
