import { Component, OnInit } from '@angular/core';
import { DataAum } from '../DataAum.model';
import { DataAumService } from '../services/data-aum.service';

@Component({
  selector: 'app-search-data-aum',
  templateUrl: './search-data-aum.component.html',
  styleUrls: ['./search-data-aum.component.scss']
})
export class SearchDataAumComponent implements OnInit {
  id: string;
  date: string;
  assetType: string;
  client: string;
  value: string;
  dataAums: DataAum[];
  dataAum: DataAum;

  constructor(private dataAumService: DataAumService) { }

  ngOnInit(): void {
  }

  search(): void {
    // Clear the dataAums array before performing a new search
    this.dataAums = [];

    // Check if all criteria are empty
    if (!this.id && !this.date && !this.assetType && !this.client && !this.value) {
      // Call findAll() if all criteria are empty
      this.dataAumService.findAll().subscribe(
        data => {
          this.dataAums = data;
        },
        error => {
          console.error('Error fetching data', error);
        }
      );
    } else {
      // Call search() with criteria if any are provided
      this.dataAumService.search(this.id, this.date, this.assetType, this.client, this.value).subscribe(
        data => {
          this.dataAums = data;
        },
        error => {
          console.error('Error fetching data', error);
        }
      );
    }
  }
}