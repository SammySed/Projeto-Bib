import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Leitor } from '../Models/leitor';
import { LeitorService } from '../service/leitor.service';


@Component({
  selector: 'app-leitor',
  templateUrl: './leitor.component.html',
  styleUrls: ['./leitor.component.css']
})

export class LeitorComponent implements OnInit {
  constructor(
    private router: Router,
    private leitorService: LeitorService,
    private route: ActivatedRoute
  ) {}

  model: Leitor = new Leitor();

  id!: number;

  voltarParaLista() {
    this.router.navigate(['/leitores']);
  }

  obterPorId(id:number){

    this.leitorService.ObterPorId(id).subscribe({
      error: (e) => { console.log(e) },
      next: (dados) => { this.model = dados },
      });
  }

  submit(): void {


    if (this.id > 0) {

      this.leitorService.Editar(this.id,this.model).subscribe({
        error: (e) => { console.log(e) },
        next: (dados) => {
          alert('Leitor alterado com sucesso!');
          this.voltarParaLista();
         },
        });

    } else {
      this.leitorService.Adicionar(this.model).subscribe({
        error: (e) => { console.log(e) },
        next: (dados) => {
          alert('Leitor cadastrado com sucesso!');
          this.voltarParaLista();
         },
        });
    }
  }

  ngOnInit(): void {

    this.id =  this.route.snapshot.params['id'];

    if (this.id){
      this.obterPorId(this.id);
    }
  }
}
