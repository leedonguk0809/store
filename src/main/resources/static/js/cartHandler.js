var modal = document.getElementById("quantityModal");
var currentRow;

function openModal(button) {
    currentRow = button.closest("tr");
    var currentQuantity = currentRow.querySelector(".quantity").textContent;
    document.getElementById("modalQuantity").value = currentQuantity;
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

    closeModal();

    if (newQuantity == 0) {
        deleteRow(currentRow.querySelector("button"));
    }
}

function deleteRow(button) {
    var row = button.closest("tr");
    row.remove();
}

function closeModal() {
    modal.style.display = "none";
}

window.onclick = function (event) {
    if (event.target == modal) {
        closeModal();
    }
};


const createOrder = () => {
    const selectedItems = [];
    document.querySelectorAll('.cart-item-checkbox:checked').forEach(checkbox => {
        let id = checkbox.getAttribute('data-item-id');
        var quantityElement = document.getElementById('order-quantity-'+id);
        var quantityValue = quantityElement.textContent.trim();
        selectedItems.push({
            itemId: id,
            quantity: quantityValue,
        });
    });

    // JSON으로 변환
    const jsonData = JSON.stringify(selectedItems);
    console.log(jsonData);
}