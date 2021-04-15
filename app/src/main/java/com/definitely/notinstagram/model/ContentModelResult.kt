package com.definitely.notinstagram.model

class ContentModelResult(
    var artistName: String,
    var collectionName: String,
    var artworkUrl100: String,
    var collectionPrice: Float,
    var currency: String,
    var primaryGenreName: String,
) {
    override fun toString(): String {
        return "\n\nTRACK:" +
                "\nArtist: $artistName" +
                "\nTrack Name: $collectionName" +
                "\nArtwork: $artworkUrl100" +
                "\nPrice: $collectionPrice $currency" +
                "\nGenre: $primaryGenreName"
    }
}