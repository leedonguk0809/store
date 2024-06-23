
    document
    .getElementById("addProductBtn")
    .addEventListener("click", function () {
    document.getElementById("productModal").style.display = "block";
});

    // 모달 닫기
    document
    .getElementsByClassName("close")[0]
    .addEventListener("click", function () {
    document.getElementById("productModal").style.display = "none";
});

    // 모달 외부 클릭 시 닫기
    window.addEventListener("click", function (event) {
    if (event.target == document.getElementById("productModal")) {
    document.getElementById("productModal").style.display = "none";
}
});

    // 폼 데이터를 JSON으로 변환하여 서버로 전송
    document
    .getElementById("productForm")
    .addEventListener("submit", function (event) {
    event.preventDefault(); // 기본 제출 동작 방지

    // 입력된 데이터 가져오기
    var productName = document.getElementById("productName").value;
    var productPrice = document.getElementById("productPrice").value;
    var stockQuantity = document.getElementById("stockQuantity").value;
    var productDescription =
    document.getElementById("productDescription").value;
    var productImage = document.getElementById("productImage").files[0];

    // FormData 생성
    var formData = new FormData();
    formData.append("name", productName);
    formData.append("price", productPrice);
    formData.append("quantity", stockQuantity);
    formData.append("info", productDescription);
    formData.append("itemImage", productImage);

    var jsonObject = {};
    formData.forEach(function (value, key) {
    jsonObject[key] = value;
});
    var jsonData = JSON.stringify(jsonObject);
    console.log(jsonData);
    // fetch API를 사용하여 서버에 전송
    fetch("http://127.0.0.1:8080//items/add-product", {
    method: "POST",
    headers: {
    "Content-Type": "application/json",
},
    body: jsonData,
})
    .then((response) => {
    if (!response.ok) {
    throw new Error("상품 추가 실패");
}
    return response.json(); // 서버에서 반환한 JSON 읽기
})
    .then((data) => {
    alert("상품 추가 성공: " + data.message);
    document.getElementById("productModal").style.display = "none"; // 모달 닫기
})
    .catch((error) => {
    alert("상품 추가 실패: " + error.message);
});
});