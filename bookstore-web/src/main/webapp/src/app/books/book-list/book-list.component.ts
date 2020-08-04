import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/models/book.model";
import {BookService} from "../../shared/services/book.service";



@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  books: Book[];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(): void {
    this.bookService.getBooks().subscribe(
      books => {
        this.books = books
        this.books.sort((b1, b2) => b1.id - b2.id)
      }
    )
  }
}
