// JavaScript for handling edit modal opening
function openEditModal(itemId, itemPrice, itemQuantity, itemInfo) {
    document.getElementById('editProductId').value = itemId;
    document.getElementById('editProductPrice').value = itemPrice;
    document.getElementById('editStockQuantity').value = itemQuantity;
    document.getElementById('editProductDescription').value = itemInfo;
    document.getElementById('editProductModal').style.display = 'block';
}

// Close the modal when clicking the close button
document.querySelectorAll('.close').forEach(closeButton => {
    closeButton.addEventListener('click', () => {
        document.getElementById('productModal').style.display = 'none';
        document.getElementById('editProductModal').style.display = 'none';
    });
});

// Close the modal when clicking outside the modal content
window.onclick = function(event) {
    if (event.target == document.getElementById('productModal')) {
        document.getElementById('productModal').style.display = 'none';
    }
    if (event.target == document.getElementById('editProductModal')) {
        document.getElementById('editProductModal').style.display = 'none';
    }
};

// Open the add product modal
document.getElementById('addProductBtn').addEventListener('click', () => {
    document.getElementById('productModal').style.display = 'block';
});

// Fetch items and populate the table
async function fetchItems() {
    const response = await fetch('/items');
    const items = await response.json();


    const itemTableBody = document.getElementById('itemTableBody');
    itemTableBody.innerHTML = '';
    items.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.itemId}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td><a href="#" onclick="openEditModal('${item.itemId}', '${item.price}', '${item.quantity}', '${item.info}')">수정</a></td>
        `;
        itemTableBody.appendChild(row);
    });
}

// Fetch items when the page loads
document.addEventListener('DOMContentLoaded', fetchItems);

// Handle the submission of the edit product form
document.getElementById('editProductForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const formData = new FormData(this);

    const itemId = document.getElementById('editProductId').value; // itemId를 직접 가져옴
    const price = document.getElementById('editProductPrice').value;
    const quantity = document.getElementById('editStockQuantity').value;
    const info = document.getElementById('editProductDescription').value;

    const updatedData = {
        price: formData.get('price'),
        quantity: formData.get('quantity'),
        info: formData.get('info')
    };

    const response = await fetch(`/items/${itemId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedData)
    });

    if (response.ok) {
        alert('상품이 성공적으로 수정되었습니다.');
        document.getElementById('editProductModal').style.display = 'none';
        fetchItems();
    } else {
        alert('상품 수정 실패');
    }
});

// 상품 추가 폼 제출을 처리하는 기능
document.getElementById('productForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    console.log(formData);
    const response = await fetch('/items', {
        method: 'POST',
        body: formData
    });

    if (response.ok) {
        alert('상품이 성공적으로 추가되었습니다.');
        document.getElementById('productModal').style.display = 'none';
        fetchItems();
    } else {
        alert('상품 추가 실패');
    }
});
