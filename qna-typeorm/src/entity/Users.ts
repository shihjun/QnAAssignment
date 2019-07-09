import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from "typeorm";
import { Questions } from "./Questions";
import { Answers } from "./Answers";

@Entity()
export class Users {

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    name: string;

    @Column()
    email: string;

    @OneToMany(type => Questions, question => question.user)
    questions: Questions[]

    @OneToMany(type => Answers, answer => answer.user)
    answers: Answers[]

}