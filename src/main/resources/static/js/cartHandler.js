var modal = document.getElementById("quantityModal");
var currentRow;

function openModal(button) {
    var tr = button.closest('.cart__list__detail');
    var itemId = tr.querySelector('[data-item-id]').getAttribute('data-item-id');
    console.log('Item ID:', itemId);
    //document.getElementById('modalItemId').value = itemId;

    currentRow = button.closest("tr");
    // var currentQuantity = currentRow.querySelector(".quantity").textContent;
    currentQuantity = document.getElementById(`order-quantity-${itemId}`).value;
    modal.style.display = "block";
}

function closeModal() {
    modal.style.display = "none";
}


function changeQuantity(amount) {
    var quantityInput = document.getElementById("modalQuantity");
    var currentQuantity = parseInt(quantityInput.value);
    if (currentQuantity + amount > 0) {
        quantityInput.value = currentQuantity + amount;
    }
}

function applyQuantity() {
    var itemId = currentRow.querySelector('[data-item-id]').getAttribute('data-item-id');
    var newQuantity = document.getElementById("modalQuantity").value;
    var quantityElement = currentRow.querySelector(".quantity");
    quantityElement.textContent = newQuantity;

    var unitPrice = parseInt(
        currentRow
            .querySelector(".unit-price")
            .textContent.replace(/[^0-9]/g, "")
    );
    var totalPriceElement = currentRow.querySelector(".total-price");
    var totalPrice = unitPrice * newQuantity;
    totalPriceElement.textContent = totalPrice.toLocaleString() + "원";

    closeModal(itemId,newQuantity);

    if (newQuantity == 0) {
        deleteRow(currentRow.querySelector("button"));
    }
}

function deleteRow(button) {
    var row = button.closest("tr");
    var cartItemId = row.querySelector('.cart-item-checkbox').getAttribute('data-cart-item-id');
    row.remove();

    fetch(`/cart/${cartItemId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('수정 요청 중 문제가 발생하였습니다.');
            }
            // 응답이 성공적으로 처리되면 페이지 새로고침
            location.reload();
        })
        .catch(error => {
            console.error('오류 발생:', error);
            // 오류 처리 로직 추가
        });
}
function closeModal(itemId,newQuantity) {
    modal.style.display = "none";
    fetch('/cart/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            itemId: itemId,
            quantity: newQuantity
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('수정 요청 중 문제가 발생하였습니다.');
            }
            // 응답이 성공적으로 처리되면 페이지 새로고침
            location.reload();
        })
        .catch(error => {
            console.error('오류 발생:', error);
            // 오류 처리 로직 추가
        });
}

window.onclick = function (event) {
    if (event.target == modal) {
        closeModal();
    }
};


const createOrder =  () => {
    let check = false;
    const selectedItems = [];
    let totalPrice = 0;
    let totalCount = 0;
    document.querySelectorAll('.cart-item-checkbox:checked').forEach(checkbox => {
        check=true;
        let id = checkbox.getAttribute('data-item-id');
        var quantityElement = document.getElementById('order-quantity-' + id);
        var quantityValue = quantityElement.textContent.trim();
        var priceElement = document.getElementById('order-price-' + id);
        totalPrice += parseInt(priceElement.textContent.trim());
        totalCount += parseInt(quantityValue);
        selectedItems.push(
            {
                itemId: parseInt(id),
                count: parseInt(quantityValue),
            }
        );
    });

    if(check===false){
        alert('장바구니가 비어있습니다.');
        return;
    }

    const request = {
        totalPrice: parseInt(totalPrice),
        totalCount: parseInt(totalCount),
        itemCountList: selectedItems
    };
    console.log(request);
    // JSON으로 변환
    const jsonData = JSON.stringify(request);
    console.log(jsonData);

    fetch('/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => {
            console.log(response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            return response.json(); // 서버에서 반환된 JSON 데이터를 읽기
        })
        .then(data => {
            window.location.href = `/orders/view?orderId=${data.id}`;
            console.log(data)
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}