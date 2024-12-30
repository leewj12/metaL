import React from 'react';
import { Editor } from '@tinymce/tinymce-react';

export default function App() {
  return (
    <Editor
      apiKey="88ikjypj4p2o3x4ctotyed67fd5ait5vchpy6rxeo4oy25ab"
      init={{
        resize: false,
        height: 800,
        menubar: true,
        plugins: [
        'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview', 'anchor',
        'searchreplace', 'visualblocks', 'code', 'fullscreen', 'insertdatetime', 'media', 'table',
        'autosave', 'emoticons', 'save', 'wordcount', 'checklist'
        ],
        toolbar: 'insertdatetime | undo redo | formatselect | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image media | preview wordcount | save checklist emoticons',
        insertdatetime_dateformat: '%Y-%m-%d',
        insertdatetime_timeformat: '%H:%M:%S',
        file_picker_callback: (callback, value, meta) => {
          if (meta.filetype === 'image') {
            const input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');
            input.onchange = () => {
              const file = input.files[0];
              const reader = new FileReader();
              reader.onload = () => {
                callback(reader.result, { alt: file.name });
              };
              reader.readAsDataURL(file);
            };
            input.click();
          }
        },
        content_style: "body { font-family:Helvetica,Arial,sans-serif; font-size:14px }",
      }}
    />
  );
}
