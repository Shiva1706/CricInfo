import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../service/api-call.service';
import { error, log } from 'console';

@Component({
  selector: 'app-point-table',
  standalone: true,
  imports: [],
  templateUrl: './point-table.component.html',
  styleUrl: './point-table.component.css'
})
export class PointTableComponent implements OnInit {
  pointTable: any;
  tableHeading: any;

  constructor(private _api:ApiCallService){};
  
  ngOnInit(): void {
    this._api.getPointTable().subscribe({
      next: data =>{
        this.pointTable = data;
        console.log(this.pointTable);
        this.tableHeading=[...this.pointTable[0]];
        console.log(this.tableHeading);
        
      },
      error:error => {
        console.log(error);
        
      }
    })
  }
;
}
