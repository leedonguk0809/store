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