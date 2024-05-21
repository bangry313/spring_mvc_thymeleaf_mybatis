const httpRequest = function (url) {
    return fetch(url).then(response => response.json());
}

/** 이벤트타겟에 이벤트 핸들러 연결(등록) */
const eventRegister = function () {
    document.registerForm.addEventListener("submit", handleSubmitButton);
    // id input에 input 이벤트 핸들러 등록
    document.querySelector("#id").addEventListener("input", handleChangeInput);

    document.querySelectorAll("form input")
        .forEach(function (input) {
            input.addEventListener("click", handleClickInput);
        });
}

// 아이디 중복 체크 이벤트 처리
const handleChangeInput = async function (event) {
    let inputId = event.target.value;
    const url = `/member/idcheck/${inputId}`;
    const object = null;
    let resultMessage = null;
    // 유효성 검증
    if (inputId.length >= 6 && inputId.length <= 10) {
        const object = await httpRequest(url);
        if (object.result) {
            resultMessage = `<span style="color: blue">${object.message}</span>`;
        } else {
            resultMessage = `<span style="color: red">${object.message}</span>`;
        }
    } else {
        resultMessage = `<span style="color: red">아이디는 6자 이상 10자 이하여야 합니다.</span>`;
    }
    showIdResult(resultMessage);
}

// 아이디 입력 관련 메시지 출력
const showIdResult = function (message) {
    const resultView = document.querySelector("#dupResult");
    if (resultView) {
        resultView.innerHTML = message;
    }
}

// 회원 가입 이벤트 처리
const handleSubmitButton = function (event) {
    event.preventDefault();
    const idInput = event.target.id;
    const nameInput = event.target.name;
    const emailInput = event.target.email;
    const passwdInput = event.target.passwd;
    const rePasswdInput = event.target["re-passwd"];
    if (Validator.isEmpty(idInput.value)) {
        // alert('아이디를 입력하여 주세요.');
        showErrorMessage(idInput, "아이디를 입력하여 주세요.");
        return;
    }

    if (Validator.isEmpty(nameInput.value)) {
        // alert('이름을 입력하여 주세요.');
        showErrorMessage(nameInput, "이름을 입력하여 주세요.");
        return;
    }

    if (Validator.isEmpty(emailInput.value)) {
        // alert('이메일을 입력하여 주세요.');
        showErrorMessage(emailInput, "이메일을 입력하여 주세요.");
        return;
    }

    if (Validator.isEmpty(passwdInput.value)) {
        alert('비밀번호를 입력하여 주세요.');
        //showErrorMessage(passwdInput, "비밀번호를 입력하여 주세요.");
        return;
    }

    if (passwdInput.value !== rePasswdInput.value) {
        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        //showErrorMessage(passwdInput, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
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

// 아이디 필드 클릭 이벤트 처리
const handleClickInput = function (event) {
    event.target.removeAttribute("style");
    event.target.value = "";
}

/** entry point */
function main() {
    eventRegister();
}
main();
