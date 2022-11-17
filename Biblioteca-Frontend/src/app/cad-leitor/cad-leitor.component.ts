import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Leitor } from '../Models/leitor';
import { LeitorService } from '../service/leitor.service';

@Component({
  selector: 'app-cad-leitor',
  templateUrl: './cad-leitor.component.html',
  styleUrls: ['./cad-leitor.component.css']
})
export class CadLeitorComponent implements OnInit
{

  constructor(private route:         Router,
              private leitorservice: LeitorService

              ) {}

  leitores!: Leitor[];

  ngOnInit(): void
  {
    this.obterTodos();
  }

  obterTodos()
  {
    this.leitorservice.ObterTodos().subscribe({
      error: (e) =>
      {
        console.log(e);
      },
      next: (dados) =>
      {
        this.leitores = dados;
      },
    });
  }

  cadastrarLeitor()
  {
    this.route.navigate(['/leitor']);
  }

  removerLeitor(id: number)
  {
    let isExecuted = confirm('Deseja realmente remover este leitor?');

    if (isExecuted) {
      this.leitorservice.Deletar(id).subscribe({
        next: (d) => {
          this.obterTodos();
        },
        error: (e) => {
          console.log(e);
        },
      });
    }
  }
}
