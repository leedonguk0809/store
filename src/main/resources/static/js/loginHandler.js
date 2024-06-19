function login() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var rememberMe = document.getElementById('rememberMe').checked;

    const loginData = {
        email: email,
        password: password,
        rememberMe: rememberMe
    };

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            window.location.href = "/";
        })

        .catch(error => {
            // Error handling
            console.error("로그인 실패:", error.message);
            if (error.message.includes('400')) {
                alert("이메일 또는 비밀번호가 잘못되었습니다.");
            }
        });
}