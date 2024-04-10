const vocabulary = "http://localhost:1150/vocabulary";
var eEdit = '<a class="h4 text-primary"><i class="fa-solid fa-pen-to-square"></i></a>';
var eDelete = '<a class="h4 text-danger"><i class="fa-solid fa-trash-can"></i></a>';

axios.get(vocabulary)
  .then(response => {
    const data = response.data;
    const table = document.getElementById('myTable');

    // เพิ่มข้อมูลในตาราง
    data.forEach(item => {
        const row = table.insertRow();
        const category = row.insertCell();
        category.textContent = item.type; // เลือกเฉพาะค่า category
        const eng = row.insertCell();

        eng.textContent = item.eng;  // เลือกเฉพาะค่า eng
        const thai = row.insertCell();
        thai.textContent = item.pronunciation; // เลือกเฉพาะค่า thai
        const pronunciation = row.insertCell();
        pronunciation.textContent = item.thai; // เลือกเฉพาะค่า pronunciation
        
        const cellEdit = row.insertCell();
        const editLink = document.createElement('a');
        editLink.classList.add('h4', 'text-primary');
        editLink.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>';
        cellEdit.appendChild(editLink);

        const cellDelete = row.insertCell();
        const deleteLink = document.createElement('a');
        deleteLink.classList.add('h4', 'text-danger');
        deleteLink.innerHTML = '<i class="fa-solid fa-trash-can"></i>';
        cellDelete.appendChild(deleteLink);
      });

    // เพิ่มตารางลงใน DOM
    document.body.appendChild(table);
  })
  .catch(error => console.error('เกิดข้อผิดพลาดในการดึงข้อมูล:', error));
  // end axios api

  

  // popover
document.querySelectorAll('[data-bs-toggle="popover"]')
.forEach(popover => {
  new bootstrap.Popover(popover)
});

// modal


// java script


// end java script
// jquery
$('.social a').prop('target','_blank');


// end jquery