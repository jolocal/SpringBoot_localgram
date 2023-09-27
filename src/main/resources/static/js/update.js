// (1) 회원정보 수정
function update(userId,event){

    event.preventDefault(); // form 태그 액션 막기

    // form 태그의 id값
    // serialize: form 태그 안에 모든 info값들
    let data = $("#profileUpdate").serialize(); //key-value

    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json" // 응답받을 데이터를 json으로 파싱
    }).done(res=>{ // HttpStatus 상태코드 200번대
        console.log("성공",res);
        location.href = `/user/${userId}`;
    }).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐 때
        console.log(error);
        if (error.data == null){
            alert("회원정보 수정에 실패하였습니다. \n원인 :" + error.responseJSON.message);
        }else {
            alert("회원정보 수정에 실패하였습니다. \n원인 :" + JSON.stringify(error.responseJSON.data));
        }
    });

}

