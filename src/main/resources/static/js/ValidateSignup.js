async function checkEmail(email) {
    try {
        const response = await fetch(`/check-email?email=${encodeURIComponent(email)}`);
        console.log(response);
        if (!response.status===200) {
            throw new Error('서버에서 이메일 확인 요청에 실패했습니다.');
        }
        const data = await response.json();
        console.log(data)
        return data;
    } catch (error) {
        console.error('이메일 확인 요청 실패:', error);
        return { status: 'error', message: '서버 오류가 발생했습니다.' };
    }
}

document.getElementById('email-valid-btn').addEventListener('click', async () => {
    const emailInput = document.getElementById('email-input').value;
    const result = await checkEmail(emailInput);
    handleCheckResult(result);
});


function handleCheckResult(result) {
    const statusElement = document.getElementById('emailError');
    console.log(result);
    if (result.use === 'disable') {
        statusElement.textContent = '중복된 이메일입니다.';
        statusElement.style.color = 'red';
    } else if (result.use === 'available') {
        statusElement.textContent = '사용 가능한 이메일입니다.';
        statusElement.style.color = 'green';
    } else {
        statusElement.textContent = '이메일 확인 중 오류가 발생했습니다.';
        statusElement.style.color = 'orange';
    }
}

document.addEventListener('DOMContentLoaded',function(){
    const password = document.getElementById('password')
    const passwordRetry = document.getElementById('passwordRetry')
    const passwordErrorMessage = document.getElementById('passwordError')

    const telNumber = document.getElementById('telNumber');
    const telNumberErrorMessage = document.getElementById('telNumberError')



    function validatePassword() {
        let passwordInput = password.value.trim(); // 입력값을 얻고 앞뒤 공백을 제거
        let passwordRetryInput = passwordRetry.value.trim();

        if(passwordInput !== passwordRetryInput){
            passwordErrorMessage.style.display = 'block'
        }
        else{
            passwordErrorMessage.style.display = 'none'
        }
    }

    function validateTelNumber(){
        let telNumberValue = telNumber.value.trim();
        if (telNumberValue.length !== 11){
            telNumberErrorMessage.style.display = 'block'
        }
        else{
            telNumberErrorMessage.style.display = 'none'
        }
    }

    password.addEventListener('input', validatePassword);
    passwordRetry.addEventListener('input', validatePassword);
    telNumber.addEventListener('input',validateTelNumber);
});