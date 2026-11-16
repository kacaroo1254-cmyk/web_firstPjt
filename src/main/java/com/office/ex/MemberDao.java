package com.office.ex;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {
	
	// Dao는 db와 통신만
	
	Map<String, MemberDto> memDB = new HashMap<String, MemberDto>();

	public int insertNewMember(MemberDto memberDto) {
		
		memDB.put(memberDto.getMemId(), memberDto);
		
		return memDB.get(memberDto.getMemId()) != null 
				?
				MemberService.SIGN_UP_OK
				:
				MemberService.SIGN_UP_NG;
		
	}

	public MemberDto selectMemberByMemIdAndMemPw(String memId) {
		
		return memDB.get(memId);
		
	}

}
