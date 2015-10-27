class String

  # Transform a string into a form that makes for an acceptable title.
  #
  #   "this is a string".titlecase
  #   #=> "This Is A String"
  #
  # @author Eliazar Parra
  # @author Angelo Lakra (apostrophe fix)

  def titlecase
    tr('_', ' ').
    gsub(/\s+/, ' ').
    gsub(/\b\w/){ $`[-1,1] == "'" ? $& : $&.upcase }
  end

end

module StringFilters

  def removehiphen(input)
    input.gsub('-',' ').titlecase
  end

  def titlecase(input)
    input.gsub('-',' ').titlecase
  end

end

Liquid::Template.register_filter(StringFilters)
