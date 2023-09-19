// (1) 회원정보 수정
function update(userId){
    // form 태그의 id값
    // serialize: form 태그 안에 모든 info값들
    let data = $("#profileUpdate").serialize();

    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json" // 응답받을 데이터를 json으로 파싱
    }).done(res=>{ // res : 자바스크립트 오브젝트
        console.log("update 성공");
        location.href = `/user/${userId}`;
    }).fail(error=>{
        console.log("update 실패");
    });

}