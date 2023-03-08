

## WYSTAWA KOTÓW - CAT SHOW 


### Relevant links: 
- ./api/cat/all - GET list of all registered cats, group bt breeds
- ./api/cat/results - GET results, shows 3 first places at every breed group and result of voting people
- ./api/cat/{catId}/{tick`etId}/vote - PATCH it's opportunity to vote at cat with id ('catId') you should get your ticket id ('ticketId'), you can vote only at ONE CAT!
- ./api/cat/{catId}/{judgeId}/vote/{points} - PATCH it's link for judges with id ('judgeId') at cat ('catId'), judges can give points between 0-10 ('points'), judge can vote once at every cat!

### H2 console data base 
  console is available at './h2' and data source 'jdbc:h2:mem:showCatDb'
