class CustomTitle < Liquid::Block
  def initialize(tag_name, markup, tokens)
     super
     @title = tokens.join
  end

  def render(context)
    context.registers[:page]['title'] = super
    ''
  end
end

Liquid::Template.register_tag('customtitle', CustomTitle)
