const vocabulary = "http://localhost:1150/vocabulary";

document.getElementById("formNewVocabulary").addEventListener("submit", function(event) {
    // ป้องกันการ submit ฟอร์มธรรมดา
    event.preventDefault();

    // ส่งข้อมูลฟอร์มไปยังเซิร์ฟเวอร์ และเมื่อเสร็จสมบูรณ์จะทำการ redirect
    axios.post(vocabulary, new FormData(document.getElementById("formNewVocabulary")))
    .then(response => {
        // ตรวจสอบสถานะการตอบกลับ
        if (response.status === 201) {
            // หากสำเร็จ ให้ redirect ไปยังหน้าเดิม
            window.location.href = "http://127.0.0.1:5500/src/main/resources/templates/vocabularies.html";
        } else {
            // หากไม่สำเร็จ ให้แสดงข้อความผิดพลาดหรือทำการจัดการอื่น ๆ
            console.error("Failed to submit form:", response.statusText);
        }
    })
    .catch(error => {
        console.error("Error:", error);
    });
});

