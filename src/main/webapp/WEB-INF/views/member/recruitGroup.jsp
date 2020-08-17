<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../resources/css/recruitGroup.css">
</head>
<body>
    <div id="body_wrapper">
    	<jsp:include page="../template/header.jsp"/>
        <div id="container">
            <div class="register">
                <div class="register-header">
                    <h1>스터디 만들기</h1>
                </div>
                <div class="register-body">
                    <div class="register-body-table">
                        <form id="recruitmentRegist" action="/member/registGroupOk">
                        <table border=1>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" id="title" name=title></td>
                            </tr>
                            <tr>
                                <td>지역</td>
<!--                                <td><input type="button" value="추가"></td> -->
								<td><input type="text" id="location" name="location"></td>
                            </tr>
                            <tr>
                                <td>주제</td>
<!--                                <td><input type="button" value="추가"></td> -->
                            		<td><input type="text" id="location" name="location"></td>			
                            </tr>
                            <tr>
                                <td>인원</td>
                                <td><select id="count">
                                    <option >3</option>
                                    <option >4</option>
                                    <option >5</option>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea></textarea></td>
                            </tr>
                            <tr> 
                                <td colspan=2 id="BtnRow"><input type="submit" value="등록"></td>
                            </tr>
                        </table> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../template/footer.jsp"/>
    </div>
</body>
</html>