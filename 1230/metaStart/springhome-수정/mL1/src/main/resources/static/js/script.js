document.getElementById('uploadForm').addEventListener('submit', async (event) => {
    event.preventDefault(); // 기본 폼 제출 동작 방지

    const fileInput = document.getElementById('fileInput');
    const file = fileInput.files[0]; // 선택한 파일

    if (!file) {
        alert('Please select a file to upload.');
        return;
    }

    const formData = new FormData();
    formData.append('file', file); // 'videoFile' -> 'file'

    try {
        const response = await fetch('/api/video', {
            method: 'POST',
            body: formData,
        });

        if (response.ok) {
            const result = await response.json();
            alert(`File uploaded successfully: ${result.message}`);
        } else {
            const error = await response.json();
            alert(`Error: ${error.message}`);
        }
    } catch (err) {
        console.error('Error uploading file:', err);
        alert('An error occurred while uploading the file.');
    }
});
