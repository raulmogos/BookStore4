import { Component, OnInit } from '@angular/core';
import {BookService} from "../../shared/services/book.service";
import {Book} from "../../shared/models/book.model";

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {

  newBook: Book

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.newBook = new Book()
  }

  addOnSubmit() {
    // validation
    this.bookService.addNewBook(this.newBook)
    this.newBook.title = '';
    this.newBook.author = '';
    this.newBook.price = null;
  }

  titleOnchange(event) {
    this.newBook.title = event.target.value;
  }

  authorOnchange(event) {
    this.newBook.author = event.target.value;
  }

  priceOnchange(event) {
    this.newBook.price = event.target.value;
  }
}
