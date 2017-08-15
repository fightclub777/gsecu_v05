#gsecu_v0.5

1. 개요.
	gsecu_v0.5는 AuthenticationProvider(<authentication-provider>)에 의존했던 
	비밀번호 체크를 커스터마이징한다.
	메뉴에 Spring Security 커스텀 태그를 적용하여 사용자 권한 별 메뉴를 제공한다.

2. 목표.
	1) 비밀번호 체크를 담당하는 AuthenticationProvider 인터페이스를 구현하는 클래스를 작성하고,
	security-context.xml의 <authentication-provider>에 참조시켜 비밀번호 체크를 구현한다.
	
	2) jsp 화면의 메뉴 부분을 menu.jsp로 분리하여 include하고,
	menu.jsp에 Spring Security 커스텀태그를 적용하여 권한별로 메뉴를 보여지게 구현한다.
	
3. 주요내용.
	
	3.1. AuthenticationProvider 인터페이스.
		: authenticate(Authentication authentication) 메소드를 구현해야 하는데,
		1) 파라메터인 authentication으로부터 사용자가 입력한 username과 password를 얻어낸 뒤,
		2) 얻어낸 username으로 DB에 해당 username의 값에 해당하는 사용자가 있는지 조회.
			(없다면 UsernameNotFoundException을 발생시키자.)
		3) 해당 사용자가 있다면 사용자가 입력한 password와 DB에서 조회한 password가 같은지 비교.
			(값이 서로 다르다면 BadCredentialsException을 발생시키자.)
		
	3.2. Spring Security 커스텀 태그.
		: <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
		1) <sec:authorize access="isAuthenticated()"></sec:authorize>
			- 인증된 사용자에게 보여줄 내용을 태그 안에 기술한다.
			
		2) <sec:authorize access="isAnonymous()"></sec:authorize>
			- 인증되지 않은 사용자에게 보여줄 내용을 태그 안에 기술한다.
			
		3) <sec:authorize access="hasRole('ROLE_A')"></sec:authorize>
			- 인증된 사용자의 권한이 'ROLE_A'일 경우 보여줄 내용을 태그 안에 기술한다.
			
		4) <sec:authorize access="hasAnyRole('ROLE_A', 'ROLE_B')"></sec:authorize>
			- 인증된 사용자의 권한이 'ROLE_A', 'ROLE_B'등 여러개 일 경우 보여줄 내용을 태그 안에 기술한다.
		
4. 추가내용.
	4.1. 
		1) 
			: 
		
		2) 
			: 
			
	4.2. 
		1) 
