const addCart = () => {
    let itemId = document.getElementById('cartItemId').value;
    let cartQuantity = document.getElementById('cartQuantity').value;

    console.log(itemId,cartQuantity);

    const json = {
        "itemId" : parseInt(itemId),
        "quantity" : parseInt(cartQuantity)
    }

    fetch('/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            // 필요에 따라 추가적인 헤더를 설정할 수 있습니다 (예: 인증 토큰 등)
        },
        body: JSON.stringify(json) // JSON 데이터를 문자열로 변환하여 body에 포함
    })
        .then(response => {
            console.log(response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            return response.json(); // 서버에서 반환된 JSON 데이터를 읽기
        })
        .then(data => {
            // alert 창을 사용하여 장바구니 이동 여부를 묻기
            if (confirm('장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?')) {
                window.location.href = '/cart'; // 장바구니 페이지로 이동
            } else {
                alert('쇼핑을 계속하세요.'); // 쇼핑을 계속할 때 메시지
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}