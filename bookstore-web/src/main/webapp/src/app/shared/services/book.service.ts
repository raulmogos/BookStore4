import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";

import {Observable} from "rxjs";
import {Book} from "../models/book.model";


@Injectable()
export class BookService {
  private booksUrl = 'http://localhost:8080/api/books';
  private bookUrl = 'http://localhost:8080/api/book';

  constructor(private httpClient: HttpClient) {
  }

  getBooks(): Observable<Book[]> {
    return this.httpClient.get<Array<Book>>(this.booksUrl);
  }

  getOneBook(id): Observable<Book> {
    return this.httpClient.get<Book>(`${this.bookUrl}/${id}`);
  }

  getBooksFromIds(ids: number[]): Observable<Book[]> {
    return this.httpClient.put<Array<Book>>(`${this.booksUrl}/ids`, ids);
  }

  addNewBook(book: Book): void {
    console.log(book)
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    this.httpClient.post<Book>(this.bookUrl, book, { headers }).subscribe();
  }

  updateBook(book: Book): void {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    this.httpClient.put<Book>(this.bookUrl, book, { headers }).subscribe();
  }

  deleteBook(id: number): void {
    this.httpClient.delete<Book>(`${this.bookUrl}/${id}`).subscribe();
  }

}
