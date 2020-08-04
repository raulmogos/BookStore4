import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../../shared/services/book.service";
import {ClientService} from "../../shared/services/client.service";
import {Book} from "../../shared/models/book.model";
import {Client} from "../../shared/models/client.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  client: Client
  books: Book[]

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private bookService: BookService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number.parseInt(params.get('id'));
      this.clientService.getOneClient(id).subscribe(
        client => {
          this.client = client
          this.bookService.getBooksFromIds(this.client.booksIds).subscribe(
            books => this.books = books
          )
        }
      );
    });
  }

  updateOnSubmit(): void {
    this.clientService.updateClient(this.client);
  }

  firstNameOnchange(event): void {
    this.client.firstName = event.target.value;
  }

  lastNameOnchange(event): void {
    this.client.lastName = event.target.value;
  }

  moneySpentOnchange(event) {
    this.client.moneySpent = event.target.value;
  }

  deleteClient() {
    this.clientService.deleteClient(this.client.id);
    this.goBack();
  }

  goBack(): void {
    this.location.back();
  }
}
