const createOrder = () => {
    updateAddress();

    let data = {
        totalAmount: document.getElementById('total-amount').getAttribute("data-total-amount"),
        quantity: document.getElementById('total-count').getAttribute("data-total-count"),
        itemName : document.getElementById('order-name').getAttribute("data-order-name"),
        userId: document.getElementById('order-wrapper').getAttribute("data-user-id"),
        orderId: document.getElementById('order-info').getAttribute("data-order-id"),
    }

    console.log(data);

    let params = new URLSearchParams();

    for (let key in data) {
        if (data.hasOwnProperty(key)) {
            params.append(key, data[key]);
        }
    }

    let queryString = params.toString();

    let url = '/pay/ready?' + queryString;
    window.location.href = url;
};

const createQueryString = (data) => {
    return Object.keys(data)
        .map(key => `${encodeURIComponent(key)}=${encodeURIComponent(data[key])}`)
        .join('&');
};


const updateAddress = () => {
    const address = {
        orderId : document.getElementById('order-info').getAttribute("data-order-id"),
        zipcode:document.getElementById('zipcode').value,
        mainAddress:document.getElementById('main-address').value,
        detailAddress:document.getElementById('detail-address').value,
    }
    fetch('/orders/address', {
    method: 'PATCH',
        headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(address)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // JSON 형태로 응답을 파싱
    })
        .then(data => {
            console.log(data);
            // 성공적으로 처리된 경우의 추가 작업 수행
        })
        .catch(error => {
            console.error('Error:', error);
            // 오류 발생 시 처리 로직
        });
}