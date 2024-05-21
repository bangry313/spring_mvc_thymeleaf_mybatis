/** 이벤트타겟에 이벤트 핸들러 연결(등록) */
const eventRegister = function () {
    document.editerForm.addEventListener("submit", handleSubmitButton);
    document.querySelectorAll("form input")
        .forEach(function (input) {
            input.addEventListener("click", handleClickInput);
        });
}

// 회원 정보 수정 이벤트 처리
const handleSubmitButton = function (event) {
    event.preventDefault();
    const emailInput = event.target.email;
    const passwdInput = event.target.passwd;
    const rePasswdInput = event.target["rePasswd"];

    if (Validator.isEmpty(emailInput.value)) {
        showErrorMessage(emailInput, "이메일을 입력하여 주세요.");
        return;
    }

    if (Validator.isEmpty(passwdInput.value)) {
        alert('비밀번호를 입력하여 주세요.');
        return;
    }

    if (passwdInput.value !== rePasswdInput.value) {
        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        return;
    }

    // 입력데이터 형식 검사 추가 필요...
    event.target.submit();
}

// 검증 오류 메시지 출력
const showErrorMessage = function (input, message) {
    input.style.color = 'red';
    input.value = message;
    input.focus();
}

// input 필드 클릭 이벤트 처리
const handleClickInput = function (event) {
    event.target.removeAttribute("style");
    event.target.value = "";
}


/** entry point */
function main() {
    eventRegister();
}
main();
