import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, JoinColumn, OneToMany } from "typeorm";
import { Users } from "./Users";
import { Answers } from "./Answers";

@Entity()
export class Questions {

  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  description: string;

  @ManyToOne(type => Users, user => user.questions)
  @JoinColumn({ name: "user_id" })
  user: Users;

  @OneToMany(type => Answers, answer => answer.question)
  answers: Answers[]

}