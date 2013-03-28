package infra.piles

import infra.chains.Band
import infra.chains.Chain

class EntryContents implements Chain<EntryBand> {

    static belongsTo = [entry:Entry]

    static constraints = {
    }

    static embedded = ['bands']

    List<EntryBand> bands
}

class EntryBand implements Band<EntryBlock> {
    String id
    List<EntryBlock> atoms
    Map<String,String> styles
    String type
}