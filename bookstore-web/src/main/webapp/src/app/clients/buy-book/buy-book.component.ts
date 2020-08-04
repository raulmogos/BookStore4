import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/models/book.model";
import {Client} from "../../shared/models/client.model";
import {PurchaseService} from "../../shared/services/purchase.service";
import {ActivatedRoute} from "@angular/router";
import {ClientService} from "../../shared/services/client.service";
import {Location} from "@angular/common";
import {Purchase} from "../../shared/models/purchase.model";

@Component({
  selector: 'app-buy-book',
  templateUrl: './buy-book.component.html',
  styleUrls: ['./buy-book.component.css']
})
export class BuyBookComponent implements OnInit {

  client: Client
  booksToBuy: Array<Book>

  constructor(
    private purchaseService: PurchaseService,
    private location: Location,
    private clientService: ClientService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number.parseInt(params.get('id'));
      this.clientService.getOneClient(id).subscribe(
        client => {
          this.client = client
          this.purchaseService.getBooksForThisClient(this.client).subscribe(booksToBuy => {
            this.booksToBuy = booksToBuy
            this.booksToBuy.sort((b1, b2) => b1.id - b2.id)
          });
        }
      );
    });
  }

  goBack() {
    this.location.back();
  }

  buyThisBook(book: Book) {
    let purchase = new Purchase();
    purchase.bookId = book.id;
    purchase.clientId = this.client.id;
    purchase.pricePaid = book.price;
    this.purchaseService.addNewPurchase(purchase);
    this.goBack()
  }
}
