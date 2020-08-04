import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {BookService} from "../../shared/services/book.service";
import {Book} from "../../shared/models/book.model";
import {Client} from "../../shared/models/client.model";
import {ClientService} from "../../shared/services/client.service";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  book: Book
  clients: Client[]

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private clientService: ClientService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number.parseInt(params.get('id'));
      this.bookService.getOneBook(id).subscribe(
        book => {
          this.book = book
          console.log(this.book.clientsIds)
          this.clientService.getClientsFromIds(this.book.clientsIds).subscribe(
            clients => this.clients = clients
          )
        }
      );
    });
  }

  updateOnSubmit(): void {
    this.bookService.updateBook(this.book);
  }

  titleOnchange(event): void {
      this.book.title = event.target.value;
  }

  authorOnchange(event): void {
    this.book.author = event.target.value;
  }

  priceOnchange(event): void {
    this.book.price = event.target.value;
  }

  deleteBook() {
    this.bookService.deleteBook(this.book.id);
    this.goBack();
  }

  goBack(): void {
    this.location.back();
  }
}
