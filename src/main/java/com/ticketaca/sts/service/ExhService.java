package com.ticketaca.sts.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ticketaca.sts.vo.ExhVO;

public interface ExhService {
	public List<ExhVO> selectAllExhList() throws DataAccessException;
	public List<ExhVO> selectExhListClosing() throws DataAccessException;
	public List<ExhVO> selectExhListNearingDeadline() throws DataAccessException;
	public List<ExhVO> selectExhListOngoing() throws DataAccessException;
	public List<ExhVO> selectExhListUpcoming() throws DataAccessException;
	public ExhVO selectOneExh(int exhNo) throws DataAccessException;
	public int findMaxExhNo() throws DataAccessException;
	public void insertExh(ExhVO exhVO) throws DataAccessException;
	public void updateExh(ExhVO exhVO) throws DataAccessException;
	public int deleteExh(int exhNo) throws DataAccessException;	
}
