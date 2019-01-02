import { Component, OnInit } from '@angular/core';
import { RxStompService} from '@stomp/ng2-stompjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  statusStr = '';

  constructor(private rxStompService: RxStompService) { }

  ngOnInit() {
    this.rxStompService.connectionState$.subscribe((statusConnected) => {

      /*     CONNECTING = 0, OPEN = 1, CLOSING = 2, CLOSED = 3 */
      if ( statusConnected === 0 ) {
        this.statusStr = 'CONNECTING';
      }
      if ( statusConnected === 1 ) {
        this.statusStr = 'OPEN';
      }
      if ( statusConnected === 2 ) {
        this.statusStr = 'CLOSING';
      }
      if ( statusConnected === 3 ) {
        this.statusStr = 'CLOSED';
      }
      console.log(`Status: ${statusConnected} (${this.statusStr})`);
    });

    this.rxStompService.connected$.subscribe((statusConnected) => {

      /* CONNECTING = 0, OPEN = 1, CLOSING = 2, CLOSED = 3 */
      if ( statusConnected === 1 ) {
        console.log('La conexion esta abierta, se sincronizara...');
      }

    });
  }

}
