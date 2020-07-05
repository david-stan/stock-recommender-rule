import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/nav/navbar.component';
import { StockService } from 'src/services/stock.service';
import { RouterModule } from '@angular/router';
import { appRoutes } from 'src/routes';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { StocksThumbnailComponent } from './components/stocks-thumbnail/stocks-thumbnail.component';
import { StockListComponent } from './components/stock-list/stock-list.component';


@NgModule({
  declarations: [
    AppComponent,
    StockListComponent,
    StocksThumbnailComponent,
    NavbarComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatCheckboxModule,
    RouterModule.forRoot(
      appRoutes)
  ],
  providers: [
    StockService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
