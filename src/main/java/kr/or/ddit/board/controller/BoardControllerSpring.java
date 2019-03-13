package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;

@Controller
public class BoardControllerSpring {

	@Resource(name="boardService")
	private IBoardService boardService;
	
	@RequestMapping(path={"board"},method={RequestMethod.GET})
	public String boardGET(Model model){
		List<BoardVO> boardList = boardService.getAllBoard();
		model.addAttribute("boardList", boardList);
		return "/board/board";
	}
	
	
	@RequestMapping(path={"board"},method={RequestMethod.POST})
	public String boardPOST(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");

		BoardVO boardVO = null;
		int result = 0;
		//type이 1이면 등록
		if(type.equals("1")){
			String board_name = request.getParameter("board_name_ins");
			String use_exist = request.getParameter("use_exist_ins");
			
			boardVO = new BoardVO();
			boardVO.setBoard_name(board_name);
			boardVO.setUse_exist(use_exist);
			result = boardService.insertBoard(boardVO);
		}
		//type이 2이면 수정
		else if(type.equals("2")){
			String board_num_str = request.getParameter("board_num_upd");
			Integer board_num = board_num_str == null ? null : Integer.parseInt(board_num_str);
			String board_name = request.getParameter("board_name_upd");
			String use_exist = request.getParameter("use_exist_upd");
			
			boardVO = boardService.selectBoard(board_num);
			boardVO.setBoard_name(board_name);
			boardVO.setUse_exist(use_exist);
			result = boardService.updateBoard(boardVO);
		}
		
		if(result == 1){
			return request.getContextPath()+"/board";
		}
		return "/board/board";
	}
	
}
