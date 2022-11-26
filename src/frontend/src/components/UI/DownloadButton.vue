<template>
 <button class="btn" @mousedown="downloadFile">Скачать в pdf</button>
</template>

<script>
import html2pdf from "html2pdf.js";

export default {
 name: 'down-load-button',

 props: [
  "dom",
  "dom2",
  'nameFile'
 ],

 methods: {
   downloadFile() {
     const invoice = document.querySelector(this.dom);
     const invoice2 = document.querySelector(this.dom2);
     invoice.appendChild(invoice2)
     var opt = {
       margin: 1,
       filename: this.nameFile,
     };
     html2pdf().from(invoice).set(opt).save();
   },

   MixHTML() {
    const invoice = document.querySelector(this.dom);
    const invoice2 = document.querySelector(this.dom2);
    invoice.appendChild(invoice2)
   }
 },

 mounted() {
  this.MixHTML()
 }
};
</script>